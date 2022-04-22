using AutoMapper;
using CommonConstants.Enums;
using CommonUtils.LogFile;
using Kohyoung.Rs3.CommonBase.Base;
using Kohyoung.Rs3.MachineSettingModule.Models.GeneralInformation;
using Kohyoung.Rs3.MachineSettingModule.Models.JudgmentPolicyModels;
using Kohyoung.Rs3.RSModels.Judgment;
using Kohyoung.Rs3.RSModels.LineMachineSettings;
using Kohyoung.Rs3.RSModels.MachineSettings.BlockJudgment;
using Kohyoung.Rs3.SystemPoolManager.Settings;
using Prism.Commands;
using System;
using System.Collections.ObjectModel;
using System.Linq;
using static Kohyoung.Rs3.RSModels.MachineSettings.BlockJudgment.BlockJudgmentSettingHistory;

namespace Kohyoung.Rs3.AssistantCardModule.ViewModels.AdvancedJudgment
{
	class AdvancedJudgmentHistoryViewModel : BaseViewModel, IAdvancedJudgmentHistoryViewModel
	{
		#region Fields

		private Machine _machineInfo;
		private DefectType _defectType = DefectType.PadOverhang;
		private string _componentName = "";
		private string _defectName = "";
		private string _inspCondUidName = "";
		private bool _isEnableButtonPagePrevious;
		private bool _isEnableButtonPageNext;
		private bool _isNoSearchedData;
		private int _pageSize;
		private int _totalRow;
		private string _totalRowStr;
		private bool _isWaiting;
		private ObservableCollection<int> _optionPageSize;
		private ObservableCollection<AdvancedListItemInfoModels> _listItemInfo;
		private ObservableCollection<Change> _listIHistory;
		private BlockHistoryFilters _historyCondition;
		private ObservableCollection<PageNumberModel> _listPageNumber;
		private int _totalPage;
		private PageNumberModel _pageCurrent;
		private bool _isBlockJudgment;
		private bool _isPassWarning;
		private bool _isAutoPass;
		private DateTime? _startDate;
		private DateTime? _endDate;

		#endregion Fields

		#region Properties

		public DateTime? StartDate
		{
			get => _startDate;
			set => SetProperty(ref _startDate, value);
		}

		public DateTime? EndDate
		{
			get => _endDate;
			set => SetProperty(ref _endDate, value);
		}

		public DefectType DefectType
		{
			get => _defectType;
			set => SetProperty(ref _defectType, value);
		}

		public string ComponentName
		{
			get => _componentName;
			set => SetProperty(ref _componentName, value);
		}

		public string DefectName
		{
			get => _defectName;
			set => SetProperty(ref _defectName, value);
		}

		public string InspCondUidName
		{
			get => _inspCondUidName;
			set => SetProperty(ref _inspCondUidName, value);
		}
		public InspectionMode InspectionMode { get; set; }

		public string InspStreamUID { get; set; }

		public Defect Defect { get; set; }

		public bool IsEnableButtonPagePrevious
		{
			get => _isEnableButtonPagePrevious;
			set => SetProperty(ref _isEnableButtonPagePrevious, value);
		}

		public bool IsEnableButtonPageNext
		{
			get => _isEnableButtonPageNext;
			set => SetProperty(ref _isEnableButtonPageNext, value);
		}

		public bool IsNoSearchedData
		{
			get => _isNoSearchedData;
			set => SetProperty(ref _isNoSearchedData, value);
		}

		public int PageSize
		{
			get => _pageSize;
			set
			{
				SetProperty(ref _pageSize, value);

				if (TotalRow != 0)
				{
					PageNumberButton();
				}
			}
		}

		public ObservableCollection<int> OptionPageSize
		{
			get => _optionPageSize;
			set => SetProperty(ref _optionPageSize, value);
		}

		public ObservableCollection<AdvancedListItemInfoModels> ListItemInfo
		{
			get => _listItemInfo;
			set => SetProperty(ref _listItemInfo, value);
		}

		public ObservableCollection<Change> ListIHistory
		{
			get => _listIHistory;
			set
			{
				SetProperty(ref _listIHistory, value);
				if (_listIHistory == null || _listIHistory.Count == 0)
				{
					IsNoSearchedData = true;
				}
				else
				{
					IsNoSearchedData = false;
				}
			}
		}

		public BlockHistoryFilters HistoryCondition
		{
			get => _historyCondition;
			set => SetProperty(ref _historyCondition, value);
		}

		public ObservableCollection<PageNumberModel> ListPageNumber
		{
			get => _listPageNumber;
			set => SetProperty(ref _listPageNumber, value);
		}

		public int TotalRow
		{
			get => _totalRow;
			set
			{
				SetProperty(ref _totalRow, value);
				if (value > 0)
					TotalRowStr = value.ToString();
				else TotalRowStr = "";
			}
		}

		public string TotalRowStr
		{
			get => _totalRowStr;
			set => SetProperty(ref _totalRowStr, value);
		}

		public int TotalPage
		{
			get => _totalPage;
			set => SetProperty(ref _totalPage, value);
		}

		public PageNumberModel PageCurrent
		{
			get => _pageCurrent;
			set => SetProperty(ref _pageCurrent, value);
		}

		public bool IsWaiting
		{
			get => _isWaiting;
			set => SetProperty(ref _isWaiting, value);
		}

		public bool IsBlockJudgment
		{
			get => _isBlockJudgment;
			set
			{
				SetProperty(ref _isBlockJudgment, value);
				if (IsBlockJudgment)
				{
					IsPassWarning = false;
					IsAutoPass = false;
				}
			}
		}

		public bool IsPassWarning
		{
			get => _isPassWarning;
			set
			{
				SetProperty(ref _isPassWarning, value);
				if (IsPassWarning)
				{
					IsBlockJudgment = false;
					IsAutoPass = false;
				}
			}
		}

		public bool IsAutoPass
		{
			get => _isAutoPass;
			set
			{
				SetProperty(ref _isAutoPass, value);
				if (IsAutoPass)
				{
					IsBlockJudgment = false;
					IsPassWarning = false;
				}
			}
		}

		#endregion Properties

		#region Commands
		public DelegateCommand AdvancedJudgmentCommand { get; set; }

		public DelegateCommand<PageNumberModel> PageNumberCommand { get; set; }

		public DelegateCommand ButtonPageFirstCommand { get; set; }

		public DelegateCommand ButtonPagePreviousCommand { get; set; }

		public DelegateCommand ButtonPageNextCommand { get; set; }

		public DelegateCommand ButtonPageLastCommand { get; set; }


		#endregion Commands

		#region Constructor

		public AdvancedJudgmentHistoryViewModel()
		{
			Inital();
			InitCommands();
		}

		#endregion Constructor

		#region Functions

		private void Inital()
		{
			IsBlockJudgment = true;
			IsPassWarning = false;
			IsAutoPass = false;
			EndDate = DateTime.Now.Date;
			StartDate = EndDate?.AddDays(-7);

			_historyCondition = new BlockHistoryFilters();
			OptionPageSize = new ObservableCollection<int> { 25, 50, 100 };
			PageSize = OptionPageSize[0];
			ListPageNumber = new ObservableCollection<PageNumberModel>();

		}

		private void InitCommands()
		{
			AdvancedJudgmentCommand = new DelegateCommand(HistoryAdvancedJudgment);
			ButtonPageFirstCommand = new DelegateCommand(async () => ButtonPageFirstOnClick());
			ButtonPagePreviousCommand = new DelegateCommand(async () => ButtonPagePreviousOnClick());
			ButtonPageNextCommand = new DelegateCommand(async () => ButtonPageNextOnClick());
			ButtonPageLastCommand = new DelegateCommand(async () => ButtonPageLastOnClick());
			PageNumberCommand = new DelegateCommand<PageNumberModel>(async (payload) => PageNumberOnClick(payload));
		}

		private void HistoryAdvancedJudgment()
		{
			GetHistoryData();
		}

		public void SetMachine(Machine machine)
		{
			_machineInfo = machine;
			GetHistoryData();
		}

		/// <summary>
		/// Calculates the page number.
		/// </summary>
		private void PageNumberButton()
		{
			ListPageNumber?.Clear();

			_totalPage = (TotalRow / PageSize) + ((TotalRow % PageSize) > 0 ? 1 : 0);

			IsEnableButtonPageNext = _totalPage > 1;
			IsEnableButtonPagePrevious = false;

			PageNumberModel page;

			//format: 1 2 3 4 ... n
			for (int i = 1; i <= _totalPage; i++)
			{
				page = new PageNumberModel();
				page.PageNumber = i;

				if (_totalPage > 5 && i == 5)
					page.IsShowThreeDot = true;

				if (i <= 4 || i == _totalPage)
					page.IsShowPageNumber = true;

				ListPageNumber.Add(page);
			}

			_pageCurrent = ListPageNumber.First();
			_pageCurrent.IsChecked = true;
			_historyCondition.PageSize = PageSize;
			_historyCondition.PageNumber = 1;

			if (IsBlockJudgment)
				ListIHistory = new ObservableCollection<Change>(SettingManager.FilterHistoryBlockPassJudgmentHistory(_historyCondition).ToList());
			else if (IsPassWarning)
				ListIHistory = new ObservableCollection<Change>(SettingManager.FilterHistoryPassWarningJudgmentHistory(_historyCondition).ToList());
			else if (IsAutoPass)
				ListIHistory = new ObservableCollection<Change>(SettingManager.FilterHistoryAutoPassJudgmentHistory(_historyCondition).ToList());
		}

		/// <summary>
		/// Pages the number on click.
		/// </summary>
		/// <param name="page">The page.</param>
		private void PageNumberOnClick(PageNumberModel page)
		{
			GetPageHistoryData(page.PageNumber);

			//data db changed when selected page number -> refresh table

			_pageCurrent = page;
			_pageCurrent.IsShowPageNumber = true;
			_pageCurrent.IsChecked = true;

			IsEnableButtonPageNext = _pageCurrent.PageNumber != _totalPage;
			IsEnableButtonPagePrevious = _pageCurrent.PageNumber != 1;

			if (page.PageNumber < 3 || _totalPage <= 5) return;

			foreach (var pageNumber in ListPageNumber)
			{
				pageNumber.IsShowPageNumber = pageNumber.PageNumber == 1
					|| pageNumber.PageNumber == page.PageNumber - 1
					|| pageNumber.PageNumber == page.PageNumber + 1
					|| pageNumber.PageNumber == page.PageNumber
					|| pageNumber.PageNumber == _totalPage;

				if (page.PageNumber <= _totalPage - 3)
					pageNumber.IsShowThreeDot = page.PageNumber + 2 == pageNumber.PageNumber
						|| page.PageNumber - 2 == pageNumber.PageNumber
						&& pageNumber.PageNumber != 1;
				else
					pageNumber.IsShowThreeDot = pageNumber.PageNumber == _totalPage - 3;
			}
		}

		/// <summary>
		/// Buttons the page last on click.
		/// </summary>
		private void ButtonPageLastOnClick()
		{
			PageNumberOnClick(ListPageNumber.Last());
		}

		/// <summary>
		/// Buttons the page next on click.
		/// </summary>
		private void ButtonPageNextOnClick()
		{
			PageNumberOnClick(ListPageNumber.First(c => c.PageNumber == _pageCurrent.PageNumber + 1));
		}

		/// <summary>
		/// Buttons the page previous on click.
		/// </summary>
		private void ButtonPagePreviousOnClick()
		{
			PageNumberOnClick(ListPageNumber.First(c => c.PageNumber == _pageCurrent.PageNumber - 1));
		}

		/// <summary>
		/// Buttons the page first on click.
		/// </summary>
		private void ButtonPageFirstOnClick()
		{
			PageNumberOnClick(ListPageNumber.First());
		}

		private void GetPageHistoryData(int pageNumber)
		{
			try
			{
				IsWaiting = true;
				_historyCondition.PageNumber = pageNumber;

				if (IsBlockJudgment)
					ListIHistory = new ObservableCollection<Change>(SettingManager.FilterHistoryBlockPassJudgmentHistory(_historyCondition).ToList());
				else if (IsPassWarning)
					ListIHistory = new ObservableCollection<Change>(SettingManager.FilterHistoryPassWarningJudgmentHistory(_historyCondition).ToList());
				else if (IsAutoPass)
					ListIHistory = new ObservableCollection<Change>(SettingManager.FilterHistoryAutoPassJudgmentHistory(_historyCondition).ToList());
			}
			catch (Exception ex)
			{
				LogHelper.Debug(ex.Message);
			}
			finally
			{
				IsWaiting = false;
				IsNoSearchedData = TotalRow == 0;

				if (IsNoSearchedData)
				{
					ListPageNumber?.Clear();
					IsEnableButtonPageNext = IsEnableButtonPagePrevious = false;
				}
			}
		}

		private void GetHistoryData()
		{
			try
			{
				IsWaiting = true;
				_historyCondition = new BlockHistoryFilters { MachineId = _machineInfo.MachineID, PageNumber = 1, PageSize = PageSize, FromDate = StartDate, ToDate = EndDate?.AddDays(1) };

				if (IsBlockJudgment)
					TotalRow = SettingManager.GetTotalRecordsOfHistoryBlockPassJudgment(_historyCondition);
				else if (IsPassWarning)
					TotalRow = SettingManager.GetTotalRecordsOfHistoryPassWarningJudgment(_historyCondition);
				else if (IsAutoPass)
					TotalRow = SettingManager.GetTotalRecordsOfHistoryAutoPassJudgment(_historyCondition);

				if (TotalRow > 0)
				{
					PageNumberButton();
				}
			}
			catch (Exception ex)
			{
				TotalRow = 0;
				LogHelper.Debug(ex.Message);
			}
			finally
			{
				IsWaiting = false;
				IsNoSearchedData = TotalRow == 0;

				if (IsNoSearchedData)
				{
					ListPageNumber?.Clear();
					IsEnableButtonPageNext = IsEnableButtonPagePrevious = false;
				}
			}
		}

		#endregion Functions
	}
}
